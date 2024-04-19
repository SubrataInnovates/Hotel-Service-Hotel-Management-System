package com.hms.service;

import java.util.List;

import com.hms.entity.Hotel;

public interface HotelService
{
	public Hotel addHotel(Hotel hotel);
	
	public Hotel getHotel(String hotelId);
	public List<Hotel> getHotels();
	
	public Hotel updateHotel(Hotel hotel);
	public void deleteHotel(String hotelId);

}
