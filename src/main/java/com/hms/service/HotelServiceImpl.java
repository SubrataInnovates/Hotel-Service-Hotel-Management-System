package com.hms.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entity.Hotel;
import com.hms.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService
{
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel addHotel(Hotel hotel)
	{
		 String randomUUID = UUID.randomUUID().toString();
		hotel.setHotelId(randomUUID);
		
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotel(String hotelId)
	{
		
		return hotelRepository.findById(hotelId).orElseThrow(()->new RuntimeException("No hotel found with !!"+hotelId));
	}

	@Override
	public List<Hotel> getHotels() 
	{
		return hotelRepository.findAll();
		
	}

	@Override
	public Hotel updateHotel(Hotel hotel)
	{
		Optional<Hotel> optionalHotel = hotelRepository.findById(hotel.getHotelId());
		if(optionalHotel.isPresent())
		{
			Hotel hotel2 = optionalHotel.get();
			
			hotel2.setName(hotel.getName());
			hotel2.setLocation(hotel.getLocation());
			hotel2.setAbout(hotel.getAbout());
			
			return hotelRepository.save(hotel2);
	
		}
		else 
		{
			throw new IllegalArgumentException("No Hotel found with "+hotel.getHotelId());
			
		}
	}

	@Override
	public void deleteHotel(String hotelId)
	{
		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		
		if(hotel.isPresent())
		{
			hotelRepository.delete(hotel.get());
		}
		else
		{
			throw new IllegalArgumentException("No Hotel Found with "+hotelId);
			
		}
		
	}

}
