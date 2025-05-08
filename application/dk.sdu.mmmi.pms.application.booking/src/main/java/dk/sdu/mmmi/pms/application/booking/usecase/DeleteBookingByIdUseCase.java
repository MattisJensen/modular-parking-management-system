package dk.sdu.mmmi.pms.application.booking.usecase;

import dk.sdu.mmmi.pms.application.booking.BookingRepository;

import java.util.UUID;

public class DeleteBookingByIdUseCase {
    private final BookingRepository repository;
    private final FindBookingByIdUseCase findBookingByIdUseCase;

    public DeleteBookingByIdUseCase(BookingRepository repository, FindBookingByIdUseCase findBookingByIdUseCase) {
        this.repository = repository;
        this.findBookingByIdUseCase = findBookingByIdUseCase;
    }

    public void execute(UUID id) {
        findBookingByIdUseCase.execute(id);
        repository.deleteById(id);
    }
}
