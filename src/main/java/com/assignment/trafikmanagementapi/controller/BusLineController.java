package com.assignment.trafikmanagementapi.controller;

import com.assignment.trafikmanagementapi.model.BusLineDetailsDto;
import com.assignment.trafikmanagementapi.service.BusLineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Api(value = "buslines", tags = "Trafik management API")
@RequestMapping("/api")
@RestController
public class BusLineController {

  @Autowired
  private final BusLineService busLineService;

  public BusLineController(BusLineService busLineService) {
    this.busLineService = busLineService;
  }

  @ApiOperation(value = "Returns top 10 bus lines with max number of bus stops", nickname = "getTop10BusLines",
      notes = "To get top 10 bus lines:")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "List of top 10 buslines retrieved"),
      @ApiResponse(code = 404, message = "Buslines not found")})
  @GetMapping(value = "/buslines",
      produces = {"application/json"})
  ResponseEntity<List<BusLineDetailsDto>> getTop10BusLines() {
    return new ResponseEntity<List<BusLineDetailsDto>>(busLineService.getTopBusLines(), HttpStatus.OK);
  }

}
