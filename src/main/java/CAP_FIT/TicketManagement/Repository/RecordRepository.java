package CAP_FIT.TicketManagement.Repository;

import CAP_FIT.TicketManagement.Data.TrainingRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordRepository {

  void insertRecord(TrainingRecord trainingRecord);

}
