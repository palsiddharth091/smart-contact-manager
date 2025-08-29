
package com.custom.annotations;


import jakarta.validation.constraints.NotBlank; // Ensures field is not blank
import jakarta.validation.constraints.Size;     // Ensures field size constraints
import jakarta.validation.Constraint;
import jakarta.validation.OverridesAttribute;  // Allows overriding constraint attributes
import java.lang.annotation.Documented;        // Marks annotation for Javadoc
import java.lang.annotation.ElementType;       // Specifies where annotation can be used
import java.lang.annotation.Retention;         // Specifies annotation retention policy
import java.lang.annotation.RetentionPolicy;   // Retention policy options
import java.lang.annotation.Target;            // Target options for annotation
import com.scm.scm20.constants.Messages;       // Custom messages for validation


@Documented // Marks annotation for inclusion in Javadocs
@Constraint(validatedBy = {}) // It tells Hibernate Validator to treat this annotation as a constraint composition.
@NotBlank // Applies NotBlank constraint to annotated element
@Size // Applies Size constraint to annotated element
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE}) // Specifies annotation can be used on fields, methods, parameters, and other annotations
@Retention(RetentionPolicy.RUNTIME) // Annotation is retained at runtime for reflection/validation
/**
 * The FormValidation annotation is a custom composite annotation designed to simplify and standardize validation for form fields.
 * <p>
 * By combining the @NotBlank and @Size constraints from Jakarta Bean Validation, it ensures that any field annotated with @FormValidation
 * is both non-blank and within a specified character length range. This annotation exposes attributes to customize validation messages
 * and size limits, allowing you to override the default error messages and set minimum/maximum lengths. It also supports validation groups
 * and payloads for advanced scenarios.
 * <p>
 * Usage example:
 * <pre>
 *   @FormValidation(notBlankMessage = "Name is required", sizeMessage = "Name must be 3-20 characters")
 *   private String name;
 * </pre>
 * <p>
 * This annotation reduces boilerplate and improves maintainability by applying consistent validation rules across multiple fields.
 */

public @interface FormValidation {

    String message() default "Invalid value";  // Default message is needed else hibernate validator wont work

    @OverridesAttribute(constraint = NotBlank.class, name = "message") // Allows overriding the NotBlank message with a custom value
    String notBlankMessage() default Messages.REQUIRED;

    @OverridesAttribute(constraint = Size.class, name = "message") // Allows overriding the Size message with a custom value
    String sizeMessage() default Messages.MIN_MAX_CHARACTERS;

    @OverridesAttribute(constraint = Size.class, name = "max") // Sets the maximum allowed size for the field
    int max() default 20;

    @OverridesAttribute(constraint = Size.class, name = "min") // Sets the minimum allowed size for the field
    int min() default 3;

    Class<?>[] groups() default {}; // Validation groups for advanced scenarios
    
    Class<? extends jakarta.validation.Payload>[] payload() default {}; // Payload for carrying metadata information
}
