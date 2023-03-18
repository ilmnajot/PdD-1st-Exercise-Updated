package com.company.company.entity.mappers;
import com.company.company.entity.Company;
import com.company.company.entity.Worker;
import com.company.company.entity.dto.CompanyDto;
import com.company.company.entity.dto.WorkerDto;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface WorkerMapper {
//    @Mapping //source = "Worker", target = "WorkerDto"

    WorkerDto workerToWorkerDto(Worker worker);
    Worker workerDtoToWorker(WorkerDto workerDto);

}
