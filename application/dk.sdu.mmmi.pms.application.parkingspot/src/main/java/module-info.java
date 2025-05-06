module dk.sdu.mmmi.pms.application.parkingspot {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.parkingspot;
    requires dk.sdu.mmmi.pms.core.parkinglot;

    // Visibility
    exports dk.sdu.mmmi.pms.application.parkingspot;
    exports dk.sdu.mmmi.pms.application.parkingspot.usecase;
}