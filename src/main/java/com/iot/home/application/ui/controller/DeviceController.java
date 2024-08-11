package com.iot.home.application.ui.controller;

import com.iot.home.application.service.DeviceService;
import com.iot.home.application.shared.dto.DeviceDto;
import com.iot.home.application.ui.model.request.DeviceAddRequestModel;
import com.iot.home.application.ui.model.response.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @GetMapping(path = "/{id}/record" ,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    private DeviceRest getDeviceRecord(@RequestParam(value = "period") String period,@PathVariable String id){
        DeviceRest returnValue= new DeviceRest();
        DeviceDto deviceDto= deviceService.getDeviceRecords(id,period);
        java.lang.reflect.Type listType = new TypeToken<List<RecordRest>>() {}.getType();
        ModelMapper modelMapper= new ModelMapper();
        List<RecordRest> recordRests=modelMapper.map(deviceDto.getRecordDtos(),listType);
        returnValue.setRecordEntities(recordRests);
        returnValue.setName(deviceDto.getName());
        return returnValue;
    }


    @Parameter(name = "Authorization", in = ParameterIn.HEADER, required = true, description = "Bearer token")
    @GetMapping(path = "/ids",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    private List<DeviceRest> getDeviceIds(){

        List<DeviceDto> deviceDtos= deviceService.getDevices();

        java.lang.reflect.Type listType = new TypeToken<List<DeviceRest>>() {}.getType();
        ModelMapper modelMapper= new ModelMapper();
        List<DeviceRest> returnValue= modelMapper.map(deviceDtos,listType);

        return returnValue;

    }
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
                    consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    OperationStatusModel addDevice(@RequestBody DeviceAddRequestModel deviceAddRequestModel){
        OperationStatusModel returnValue= new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.ADD_DEVICE.name());
        returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

         DeviceDto deviceDetail= deviceService.addDevices(deviceAddRequestModel.getName(),deviceAddRequestModel.getFeedKey());
         if(deviceDetail!=null){
             returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
         }

         return returnValue;
    }
}
