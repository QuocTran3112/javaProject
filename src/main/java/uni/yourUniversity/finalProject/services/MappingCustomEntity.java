package uni.yourUniversity.finalProject.services;

import uni.yourUniversity.finalProject.model.BaseEntity;

public interface MappingCustomEntity<E extends BaseEntity> {
	public E convert(Object[] data);
}
