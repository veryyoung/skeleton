package me.veryyoung.skeleton.service;

import me.veryyoung.skeleton.validator.ValidatorWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by veryyoung on 2015/3/18.
 */
public abstract class BaseService {

    protected ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    protected Validator validator = factory.getValidator();
    private ValidatorWrapper validatorWrapper;


    protected Logger logger = null;

    public BaseService() {
        logger = LoggerFactory.getLogger(getClass().getName());
    }

    protected ValidatorWrapper getValidatorWrapper() {
        if (validatorWrapper == null) {
            validatorWrapper = new ValidatorWrapper(validator);
        }
        return validatorWrapper;
    }
}
