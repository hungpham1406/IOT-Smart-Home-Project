package com.iot.home.applicaton.ui.controller;

import com.iot.home.applicaton.service.DeviceService;
import com.iot.home.applicaton.shared.dto.DeviceDto;
import com.iot.home.applicaton.ui.model.request.DeviceAddRequestModel;
import com.iot.home.applicaton.ui.model.response.DeviceRest;
import com.iot.home.applicaton.ui.model.response.OperationStatusModel;
import com.iot.home.applicaton.ui.model.response.RequestOperationName;
import com.iot.home.applicaton.ui.model.response.RequestOperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( "/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;
//    @GetMapping(path = "/{id}/record" ,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
//    private DeviceRest getDeviceRecord(@RequestParam(value = "period") String period){
//        DeviceRest returnValue= new DeviceRest();
//
//        return returnValue;
//    }
//
//    @GetMapping(path = "/ids",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
//    private List<DeviceRest> getDeviceIds(){
//        List<DeviceRest> deviceRests= new ArrayList<>();
//
//        return deviceRests;
//
//    }
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
                    consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    OperationStatusModel addDevice(@RequestBody DeviceAddRequestModel deviceAddRequestModel){
        OperationStatusModel returnValue= new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.ADD_DEVICE.name());
        returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

         DeviceDto deviceDetail= deviceService.addDevices(deviceAddRequestModel.getName());
         if(deviceDetail!=null){
             returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
         }

         return returnValue;
    }
}
