package swingy.controllers.validation;

import swingy.models.characters.heroes.Hero;
import swingy.utils.Utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

public class Validation {

    private static final Validator validator;

    static
    {
        ValidatorFactory factory = javax.validation.Validation.byDefaultProvider().configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();
        validator = factory.getValidator();
    }

    public static void validateHero(Hero hero)
    {
        Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(hero);

        if (!constraintViolations.isEmpty())
        {
            for (ConstraintViolation<Hero> violation : constraintViolations)
                Utils.printError(violation.getMessage());
        }
    }
}
