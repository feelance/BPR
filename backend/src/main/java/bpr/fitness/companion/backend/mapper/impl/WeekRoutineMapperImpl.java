package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.WeekRoutineMapper;
import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.model.entity.AccountEntity;
import bpr.fitness.companion.backend.model.entity.WeekRoutineEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for all weekRoutine operations
 */
@Service
public class WeekRoutineMapperImpl implements WeekRoutineMapper {
    /**
     * Maps to weekRoutine Entity
     * @param weekRoutine contains all weekRoutine details
     * @return WeekRoutineEntity
     */
    @Override
    public WeekRoutineEntity mapToWeekRoutineEntity(WeekRoutine weekRoutine) {
        WeekRoutineEntity weekRoutineEntity = null;
        if (weekRoutine != null){
            weekRoutineEntity = new WeekRoutineEntity();
            weekRoutineEntity.setId(weekRoutine.getId());
            weekRoutineEntity.setName(weekRoutine.getName());
            weekRoutineEntity.setNotes(weekRoutine.getNotes());
            weekRoutineEntity.setAccount(mapToAccountEntity(weekRoutine.getUserId()));
        }
        return weekRoutineEntity;
    }

    /**
     * Maps a user ID to an AccountEntity object.
     *
     * @param userId the ID of the user to map to an AccountEntity
     * @return an AccountEntity object with the specified user ID set, or {@code null} if the user ID is {@code null}
     */
    private AccountEntity mapToAccountEntity(Long userId) {
        if(userId != null){
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setId(userId);
            return accountEntity;
        }
        return null;
    }

    /**
     * Maps to weekRoutine Entity List
     * @param weekRoutineEntityList list of weekRoutineEntity
     * @return List<WeekRoutine>
     */
    @Override
    public List<WeekRoutine> mapToWeekRoutineList(List<WeekRoutineEntity> weekRoutineEntityList) {
        List<WeekRoutine> weekRoutineList = null;
        if (!CollectionUtils.isEmpty(weekRoutineEntityList)){
            weekRoutineList = new ArrayList<>();
            for (WeekRoutineEntity weekRoutineEntity : weekRoutineEntityList){
                weekRoutineList.add(mapToWeekRoutine(weekRoutineEntity));
            }
        }
        return weekRoutineList;
    }

    /**
     * Maps to weekRoutine
     * @param weekRoutineEntity weekRoutineEntity
     * @return WeekRoutine
     */
    @Override
    public WeekRoutine mapToWeekRoutine(WeekRoutineEntity weekRoutineEntity) {
        WeekRoutine weekRoutine = null;
        if (weekRoutineEntity != null){
            weekRoutine = new WeekRoutine();
            weekRoutine.setId(weekRoutineEntity.getId());
            weekRoutine.setName(weekRoutineEntity.getName());
            weekRoutine.setNotes(weekRoutineEntity.getNotes());
            weekRoutine.setUserId(mapToAccount(weekRoutineEntity.getAccount()));
        }
        return weekRoutine;
    }

    /**
     * Maps an AccountEntity object to its corresponding ID.
     *
     * @param account the AccountEntity object to map
     * @return the ID of the AccountEntity, or {@code null} if the AccountEntity is {@code null}
     */
    private Long mapToAccount(AccountEntity account) {
        if(account != null){
            return account.getId();
        }
        return null;
    }

}
