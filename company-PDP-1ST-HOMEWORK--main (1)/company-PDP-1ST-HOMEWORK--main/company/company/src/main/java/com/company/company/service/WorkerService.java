package com.company.company.service;
import com.company.company.entity.Worker;
import com.company.company.entity.mappers.WorkerMapper;
import com.company.company.repository.WorkerRepository;
import com.company.company.entity.dto.ApiResponse;
import com.company.company.entity.dto.WorkerDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final WorkerMapper mapper;


    /**
     * we can get list of workers here
     * and also convert Worker entity to workerDto
     * @return list of workers
     */
    public List<WorkerDto> getWorkers(){
        return workerRepository.findAll(Sort.by("id"))
                .stream()
                .map(mapper::workerToWorkerDto)
                .collect(Collectors.toList());
    }

    /**
     * we can create any worker data
     * @param workerDto
     * @return ApiResponse
     */
    public ApiResponse addWorker(WorkerDto workerDto) {
        boolean phoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (phoneNumber) {
            return new ApiResponse("this phone number is already existent, so please use another number", false);
        }
        Worker worker = mapper.workerDtoToWorker(workerDto);
        workerRepository.save(worker);
        return new ApiResponse("the data successfully saved", true);
    }
    /**
     * we can get any worker data by its id
     * @param id
     * @return Worker
     */
    public Worker getWorkerById(Long id){
        Optional<Worker> byId = workerRepository.findById(id);
        if (byId.isPresent()){
            return workerRepository.getReferenceById(id);
        }
        return null ;
    }
    /**
     * we can delete any worker by its id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteWorkerById(Long id){
    workerRepository.deleteById(id);
    return new ApiResponse("the data deleted successfully", true);
    }

    /**
     * we can update any worker data and save it
     * @param id
     * @param workerDto
     * @return ApiResponse
     */
    public ApiResponse editWorker(Long id, WorkerDto workerDto){
        boolean numberAndIdNot = workerRepository.existsByIdAndPhoneNumber(id, workerDto.getPhoneNumber());
        if (numberAndIdNot){
            return new ApiResponse("this number is exist in the system, pls select another one", false);
        }
        Optional<Worker> workers = workerRepository.findById(id);
        if (workers.isEmpty()){
            return new ApiResponse("this person is not exist here", false);
        }
        /**
         * here I used mapperStruck to connect dto to entity
         */
        workerDto.setId(id);
        Worker worker = mapper.workerDtoToWorker(workerDto);
//        Worker worker = new Worker();
//        worker.setId(id);
//        worker.setDepartment(workerDto.getDepartment());
//        worker.setAddress(workerDto.getAddress());
//        worker.setName(workerDto.getName());
//        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("updated", true);

    }
}
