package kamil.sowa.shipscompany.cruise

import kamil.sowa.shipscompany.ship.Ship
import kamil.sowa.shipscompany.utils.CruiseConstans
import kamil.sowa.shipscompany.utils.ShipConstans;
import lombok.RequiredArgsConstructor
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RequiredArgsConstructor
class CruiseDtoValidation extends Specification {
    private static Long[] IDS = [1L]
    private static final MUST_NOT_BE_NULL = "must not be null"
    private static final Ship ship1 = ShipConstans.createShip1(IDS[0]).build()
    private static final CruiseDto cruiseDto1 = CruiseConstans.createCruiseDto1(IDS[0], ship1.getId()).build()
    private static final DEPARTURE_FIELD = "departure"
    private static final ARRIVAL_FIELD = "arrival"
    private static final SHIP_ID_FIELD = "shipId"

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    def setup() {
        validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.getValidator()
    }

    def "firstTest"() {
        given:
        def dummy = cruiseDto1

        when:
        Set<ConstraintViolation<CruiseDto>> violations = validator.validate(dummy)

        then:
        assert violations.isEmpty()
    }

    @Unroll
    def "secondTest # "() {
        given:

        when:
        def dummy = builder
        Set<ConstraintViolation<CruiseDto>> violations = validator.validate(dummy)

        then:
        assert violations.size() == 1

        ConstraintViolation<CruiseDto> violation = violations.iterator().next()

        assert message == violation.getMessage()
        assert property == violation.getPropertyPath().toString()
        assert null == violation.getInvalidValue()

        where:
        builder | message | property
        cruiseDtoBuilder().departure(null).build() |  MUST_NOT_BE_NULL | DEPARTURE_FIELD
        cruiseDtoBuilder().shipId(null).build() |  MUST_NOT_BE_NULL | SHIP_ID_FIELD
        cruiseDtoBuilder().arrival(null).build() |  MUST_NOT_BE_NULL | ARRIVAL_FIELD
    }

    def close() {
        validatorFactory.close()
    }

    def cruiseDtoBuilder() {
        CruiseConstans.createCruiseDto1(IDS[0], ship1.getId())
    }

}
