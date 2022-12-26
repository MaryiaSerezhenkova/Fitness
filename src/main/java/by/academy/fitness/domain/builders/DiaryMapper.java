package by.academy.fitness.domain.builders;

import by.academy.fitness.domain.entity.Diary;

public class DiaryMapper {
	public DiaryMapper() {
		super();
	}

	public static Diary diaryOutputMapping(Diary diary) {
		return new Diary(diary.getUuid(), diary.getDtCreate(), diary.getDtUpdate(), diary.getProduct(), diary.getDish(),
				diary.getWeight(), diary.getMealTime());
	}
}
