package com.TeamProject.Validation.MemberValidation;

import jakarta.validation.GroupSequence;

public interface ValidationSequence {

    // name
    @GroupSequence({NameValidationGroups.NotNullGroup.class})
    interface NameValidationSequence {}
    
    // password
    @GroupSequence({PasswordValidationGroups.NotNullGroup.class, PasswordValidationGroups.SizeGroup.class, PasswordValidationGroups.PatternGroup.class})
    interface PasswordValidationSequence {}

    // email
    @GroupSequence({EmailValidationGroups.NotNullGroup.class, EmailValidationGroups.EmailGroup.class})
    interface EmailValidationSequence {}

}

