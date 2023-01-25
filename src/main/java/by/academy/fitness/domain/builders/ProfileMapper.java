package by.academy.fitness.domain.builders;

import by.academy.fitness.domain.dto.ProfileDTO;
import by.academy.fitness.domain.entity.Profile;

public class ProfileMapper {
	
	public ProfileMapper() {
		super();
	}

	public static Profile profileInputMapping(ProfileDTO dto) {
		return new Profile(dto.getHeight(), dto.getWeight(), dto.getDtBirthday(), dto.getTarget(), dto.getType(), dto.getGender());
	}
	public static Profile profileOutputMapping(Profile profile) {
		return new Profile(profile.getUuid(), profile.getDtCreate(),profile.getDtUpdate(),profile.getHeight(), profile.getWeight(), profile.getDtBirthday(), profile.getTarget(), profile.getType(), profile.getGender(), profile.getUser());
	}

}
