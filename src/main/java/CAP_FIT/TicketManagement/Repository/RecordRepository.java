package CAP_FIT.TicketManagement.Repository;

import CAP_FIT.TicketManagement.Data.TrainingRecord;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordRepository {

  /**
   * 新規カルテ情報の追加が可能
   * @param trainingRecord 新規カルテ情報
   */
  void insertRecord(TrainingRecord trainingRecord);

  /**
   * ユーザーidで指定されたユーザーのカルテ情報リストの取得が可能
   * @param userId ユーザーid
   * @return ユーザーidで指定されたユーザーのカルテ情報リスト
   */
  List<TrainingRecord> selectRecordList(String userId);

  /**
   * ユーザーidで指定されたユーザーのカルテ情報の削除が可能
   * @param id ユーザーid
   */
  void deleteRecord(String id);

}
