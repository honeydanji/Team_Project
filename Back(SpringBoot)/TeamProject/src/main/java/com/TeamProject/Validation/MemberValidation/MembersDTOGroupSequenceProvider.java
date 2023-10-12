package com.TeamProject.Validation.MemberValidation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.TeamProject.Dto.MembersDTO;

public class MembersDTOGroupSequenceProvider implements DefaultGroupSequenceProvider<MembersDTO> {

    @Override
    public List<Class<?>> getValidationGroups(MembersDTO dto) {
        List<Class<?>> groups = new ArrayList<>();
        groups.add(MembersDTO.class); // 기본 그룹

        if (dto != null) {
            if (dto.getName() == null) {
                groups.add(NameValidationGroups.class);
            }
            if (!dto.getLoginEmail().isEmpty()) {
                // Email 필드에 대한 유효성 검사 그룹을 추가
                groups.add(EmailValidationGroups.class);
            }
            if (!dto.getPassword().isEmpty()) {
                // Password 필드에 대한 유효성 검사 그룹을 추가
                groups.add(PasswordValidationGroups.class);
            }
            // 다른 필드에 대한 유효성 검사 그룹도 필요한 경우 추가 가능
        }

        return groups;
    }
}
