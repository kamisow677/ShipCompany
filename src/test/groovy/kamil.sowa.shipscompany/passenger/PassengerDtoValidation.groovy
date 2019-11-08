package kamil.sowa.shipscompany.passenger

import kamil.sowa.shipscompany.cruise.Cruise
import kamil.sowa.shipscompany.cruise.CruiseDto
import kamil.sowa.shipscompany.passanger.PassengerDto
import kamil.sowa.shipscompany.ship.Ship
import kamil.sowa.shipscompany.utils.CruiseConstans
import kamil.sowa.shipscompany.utils.PassengerConstans
import kamil.sowa.shipscompany.utils.ShipConstans
import lombok.RequiredArgsConstructor
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

@RequiredArgsConstructor
class PassengerDtoValidation extends Specification {
    private static Long[] IDS = [1L]
    private static final MUST_NOT_BE_NULL = "must not be null"
    private static final MUST_NOT_BE_BLANK = "must not be blank"
    private static final Cruise cruise = CruiseConstans.createCruise1(IDS[0], null).build()
    private static final Ship ship1 = ShipConstans.createShip1(IDS[0]).build();
    private static final PassengerDto passengerDto = PassengerConstans.createPassengerDto1(IDS[0], cruise.getId(), ship1.getId()).build()
    private static final FIRSTNAME_FIELD = "firstName"
    private static final LASTNAME_FIELD = "lastName"
    private static final CRUISE_ID_FIELD = "cruiseId"

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    def setup() {
        validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.getValidator()
    }

    def "firstTest"() {
        given:
        def dummy = passengerDto

        when:
        Set<ConstraintViolation<CruiseDto>> violations = validator.validate(dummy)

        then:
        assert violations.isEmpty()
    }

    @Unroll
    def "secondTest property: #property "() {
        given:

        when:
        def dummy = builder
        Set<ConstraintViolation<CruiseDto>> violations = validator.validate(dummy)

        then:
        assert violations.size() == 1

        ConstraintViolation<CruiseDto> violation = violations.iterator().next()

        assert message == violation.getMessage()
        assert property == violation.getPropertyPath().toString()
        assert invalidValue == violation.getInvalidValue()

        where:
        builder                                      | message          | property | invalidValue
        passengerDtoBuilder().firstName("").build()  | MUST_NOT_BE_BLANK | FIRSTNAME_FIELD | ""
        passengerDtoBuilder().lastName("").build()   | MUST_NOT_BE_BLANK | LASTNAME_FIELD | ""
        passengerDtoBuilder().cruiseId(null).build() | MUST_NOT_BE_NULL | CRUISE_ID_FIELD | null
    }

    def close() {
        validatorFactory.close()
    }

    def passengerDtoBuilder() {
        PassengerConstans.createPassengerDto1(IDS[0], cruise.getId(), ship1.getId())
    }
}