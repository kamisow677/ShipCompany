package kamil.sowa.shipscompany.ships

import kamil.sowa.shipscompany.cruise.Cruise
import kamil.sowa.shipscompany.cruise.CruiseDto
import kamil.sowa.shipscompany.ship.ShipDto
import kamil.sowa.shipscompany.utils.CruiseConstans
import kamil.sowa.shipscompany.utils.ShipConstans
import lombok.RequiredArgsConstructor
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

@RequiredArgsConstructor
class ShipDtoValidation extends Specification {
    private static Long[] IDS = [1L]
    private static final MUST_NOT_BE_NULL = "must not be null"
    private static final MUST_NOT_BE_BLANK = "must not be blank"
    private static final ShipDto shipDto = ShipConstans.createShipDto1(IDS[0]).build()
    private static final MODEL_FIELD = "model"
    private static final SERIAL_NUMBER_FIELD = "serialNumber"

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    def setup() {
        validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.getValidator()
    }

    def "firstTest"() {
        given:
        def dummy = shipDto

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
        Set<ConstraintViolation<ShipDto>> violations = validator.validate(dummy)

        then:
        assert violations.size() == 1

        ConstraintViolation<ShipDto> violation = violations.iterator().next()

        assert message == violation.getMessage()
        assert property == violation.getPropertyPath().toString()
        assert invalidValue == violation.getInvalidValue()

        where:
        builder                                     | message           | property            | invalidValue
        shipDtoBuilder().model("").build()          | MUST_NOT_BE_BLANK | MODEL_FIELD         | ""
        shipDtoBuilder().serialNumber(null).build() | MUST_NOT_BE_NULL  | SERIAL_NUMBER_FIELD | null
    }

    def close() {
        validatorFactory.close()
    }

    def shipDtoBuilder() {
        ShipConstans.createShipDto1(IDS[0])
    }
}
