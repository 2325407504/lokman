package com.aripd.common.annotation.validator;

import com.aripd.common.annotation.ContentType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Validator of content type. This is simple and not complete implementation of
 * content type validating. It's based just on
 * <code>String</code> equalsIgnoreCase method.
 *
 * @author Michal Kreuzman
 */
public class ContentTypeMultipartFileValidator implements ConstraintValidator<ContentType, CommonsMultipartFile> {

    private String[] acceptedContentTypes;
    private Long maxUploadSize;

    public void initialize(ContentType constraintAnnotation) {
        this.acceptedContentTypes = constraintAnnotation.value();
        this.maxUploadSize = constraintAnnotation.max();
    }

    public boolean isValid(CommonsMultipartFile value, ConstraintValidatorContext context) {
        if (value.getSize() > maxUploadSize) {
            return false;
        }

        if (value == null || value.isEmpty()) {
            return false;
        }

        return ContentTypeMultipartFileValidator.acceptContentType(value.getContentType(), acceptedContentTypes);
    }

    private static boolean acceptContentType(String contentType, String[] acceptedContentTypes) {
        for (String accept : acceptedContentTypes) {
            // TODO this should be done more clever to accept all possible content types
            if (contentType.equalsIgnoreCase(accept)) {
                return true;
            }
        }
        return false;
    }
}
