package com.example.travelsystem.service.ServiceImpl;

import com.example.travelsystem.model.Hotel;
import com.example.travelsystem.model.Room;
import com.example.travelsystem.repository.HotelRepository;
import com.example.travelsystem.repository.RoomRepository;
import com.example.travelsystem.repository.ReservationRepository;
import com.example.travelsystem.service.HotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importun olduğundan əmin oluruq

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    // Constructor vasitəsilə lazımi bütün repository-ləri inject edirik
    public HotelServiceImpl(HotelRepository hotelRepository,
                            RoomRepository roomRepository,
                            ReservationRepository reservationRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Hotel> searchHotels(String name) {
        return hotelRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Hotel> findHotelsByCity(String cityName) {
        return hotelRepository.findByCity_NameIgnoreCase(cityName);
    }

    @Override
    public List<Hotel> findHotelsByCountry(String countryName) {
        return hotelRepository.findByCity_Country_NameIgnoreCase(countryName);
    }

    @Override
    public Page<Hotel> getHotels(Pageable pageable) {
        return hotelRepository.findAll(pageable);
    }

    @Override
    @Transactional // Tranzaksiya daxilində işləyirik
    public Hotel saveHotel(Hotel hotel) {
        Hotel savedHotel = hotelRepository.save(hotel);
        if (savedHotel.getAmenities() != null) {
            savedHotel.getAmenities().size(); // Bazaya yazıldıqdan sonra siyahını doldururuq
        }
        return savedHotel;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true) // Səssiyanın metod bitənə qədər açıq qalmasını təmin edir
    public Hotel findHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);

        if (hotel != null && hotel.getAmenities() != null) {
            // Bu sətir Thymeleaf render olunmamışdan əvvəl Hibernate-i məcbur edir ki,
            // otelin "amenities" siyahısını verilənlər bazasından dərhal yükləsin.
            hotel.getAmenities().size();
        }

        return hotel;
    }

    @Override
    @Transactional // Tranzaksiya daxilində işləyirik
    public Hotel updateHotel(Hotel hotel) {
        Hotel updatedHotel = hotelRepository.save(hotel);
        if (updatedHotel.getAmenities() != null) {
            updatedHotel.getAmenities().size(); // Yeniləndikdən sonra siyahını doldururuq
        }
        return updatedHotel;
    }

    @Override
    @Transactional // Bu mütləqdir! Bütün silmə zənciri eyni tranzaksiyada icra olunmalıdır.
    public void deleteHotelById(Long id) {
        // 1. Silinəcək oteli tapırıq
        Hotel hotel = hotelRepository.findById(id).orElse(null);

        if (hotel != null) {
            // 2. Otelə bağlı otaqlar varsa, onları yoxlayırıq
            if (hotel.getRooms() != null) {
                for (Room room : hotel.getRooms()) {
                    // 3. Hər otağa bağlı rezervasiyaları (əgər varsa) əvvəlcə silirik
                    if (room.getReservations() != null && !room.getReservations().isEmpty()) {
                        reservationRepository.deleteAll(room.getReservations());
                    }
                }
                // 4. Otaqların özünü verilənlər bazasından silirik
                roomRepository.deleteAll(hotel.getRooms());
            }
            // 5. Artıq otel tamamilə azaddır! İndi onu silə bilərik
            hotelRepository.delete(hotel);
        }
    }
}