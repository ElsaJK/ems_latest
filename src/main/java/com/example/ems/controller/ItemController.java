package com.example.ems.controller;

import com.example.ems.exceptions.APException;
import com.example.ems.model.ApiResponse;
import com.example.ems.model.CountDTO;
import com.example.ems.model.ItemDTO;
import com.example.ems.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/item")
@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<ItemDTO>> create(@RequestBody ItemDTO itemDTO) throws APException {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            apiResponse.setResponse(itemService.create(itemDTO));
            apiResponse.setMessage("Items Created Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (APException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Error Occured While Creating Item : " + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping(value = "/items")
    ResponseEntity<ApiResponse<Page<ItemDTO>>> getAllItems(
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Page<ItemDTO> itemDTOS = itemService.
                findAllItems(PageRequest.of(page, size, Sort.by("createdDate").descending()));
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResponse(itemDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @PatchMapping("/item-update")
    public ResponseEntity<ApiResponse<CountDTO>> itemUpdate(@RequestBody ItemDTO itemDTO) throws APException {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            apiResponse.setResponse(itemService.itemUpdate(itemDTO));
            apiResponse.setMessage("Items count updated Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (APException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Error Occured While update Item count : " + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }
}
