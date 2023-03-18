package com.company.company.controller;
import com.company.company.entity.Worker;
import com.company.company.entity.dto.ApiResponse;
import com.company.company.entity.dto.WorkerDto;
import com.company.company.service.WorkerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
@AllArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    /**
     * we can get list of workers here
     * @return list of workers
     */
    @GetMapping("/all")
    public ResponseEntity<List<WorkerDto>> getALlWorkers(){
        return new ResponseEntity<List<WorkerDto>>(workerService.getWorkers(),HttpStatus.OK);
    }

    /**
     * we can create any worker data
     * @param workerDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<?> saveWorker(@Valid @RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * we can get any worker data by its id
     * @param id
     * @return Worker
     */
    @GetMapping("/{id}")
    public  ResponseEntity<Worker> getWorker(@PathVariable Long id){
        return new ResponseEntity<Worker>(workerService.getWorkerById(id),HttpStatus.OK);
    }

    /**
     * we can delete any worker by its id
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ApiResponse deleteWorker(@PathVariable Long id){
        return workerService.deleteWorkerById(id);
    }

    /**
     * we can update any worker data and save it
     * @param id
     * @param workerDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorker(@PathVariable Long id, @RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);


    }

}
