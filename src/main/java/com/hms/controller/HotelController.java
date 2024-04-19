package com.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.entity.Hotel;
import com.hms.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController
{
	@Autowired
	private HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<String> addHotel(@RequestBody Hotel hotel)
	{
		Hotel addHotel = hotelService.addHotel(hotel);
		String message="Hotel with hotel id "+addHotel.getHotelId()+" has been created successfully !!";
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId)
	{
		Hotel hotel = hotelService.getHotel(hotelId);
		return ResponseEntity.status(HttpStatus.OK).body(hotel);
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getHotels()
	{
		List<Hotel> hotels = hotelService.getHotels();
		return ResponseEntity.status(HttpStatus.OK).body(hotels);
	}
	@PutMapping("/{hotelId}")
	public ResponseEntity<String > updateHotel(@RequestBody Hotel hotel,@PathVariable String hotelId)
	{
		if(!hotelId.equals(hotel.getHotelId()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hotel not found with id "+hotelId);
		}
		
		try
		{
			Hotel updateHotel = hotelService.updateHotel(hotel);
			return ResponseEntity.status(HttpStatus.OK).body("Hotel with "+hotelId+" has been successfully updated !!");
		}
		catch (IllegalArgumentException ex)
	    {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found with ID: " + hotelId);
	    } 
	    catch (Exception ex)
	    {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the hotel.");
	    }
	}
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<String> deleteHotel(@PathVariable String hotelId) {
	    hotelService.deleteHotel(hotelId);
	    String message = "Deleted Hotel with ID " + hotelId;
	    return ResponseEntity.ok().body(message);
	}

}
