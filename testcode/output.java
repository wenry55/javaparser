import java.util.List;
import lombok.Data;

@Data
public class EapMsgBodyJobInfoResv {

	String bkeqpId;
	String siteId;
	String portId;
	String recipeDefId;
	String carrId;
	List<String> lotId;
	String batchId;
	Long batchCount;
	Long qty;
	
	List<EapMsgBodyJobInfoResvProdMtrls> prodMtrls;
}
