package dto;

import model.NodeEntity;
import model.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class NodeDTO {

    @NotNull
    private Long id;

    @NotNull
    private String user;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

    @NotNull
    private Map<String, String> tags;

    public static NodeDTO convert(NodeEntity nodeEntity){
        Map<String, String> tags = nodeEntity.getTags().stream().collect(Collectors.toMap(TagEntity::getKey, TagEntity::getValue));
        return new NodeDTO(nodeEntity.getId(), nodeEntity.getUser(), nodeEntity.getLongitude(), nodeEntity.getLatitude(), tags);
    }
}
