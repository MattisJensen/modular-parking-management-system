module dk.sdu.mmmi.pms.application.booking {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.booking;
    requires dk.sdu.mmmi.pms.application.shared;

    // Visibility
    exports dk.sdu.mmmi.pms.application.booking;
    exports dk.sdu.mmmi.pms.application.booking.usecase;
}