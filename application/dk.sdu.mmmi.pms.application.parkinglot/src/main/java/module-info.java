module dk.sdu.mmmi.pms.application.parkinglot {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.parkinglot;
    requires dk.sdu.mmmi.pms.core.parkingspot;

    // Visibility
    exports dk.sdu.mmmi.pms.application.parkinglot;
    exports dk.sdu.mmmi.pms.application.parkinglot.usecase;
}