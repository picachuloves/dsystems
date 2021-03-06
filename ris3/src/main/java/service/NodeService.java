package service;

import ru.nsu.fit.g17205.kondratev.DBApp.repository.NodeRepository;
import ru.nsu.fit.g17205.kondratev.DBApp.repository.TagRepository;
import lombok.AllArgsConstructor;
import ru.nsu.fit.g17205.kondratev.DBApp.model.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NodeService {
    @Autowired
    private final NodeRepository nodeRepository;

    @Autowired
    private final TagRepository tagRepository;

    public NodeEntity save(NodeEntity node) {
        NodeEntity nodeEntity = nodeRepository.save(node);
        return nodeEntity;
    }

    public NodeEntity getNode(Long id) {
        return nodeRepository.findById(id).orElse(null);
    }

    public NodeEntity update(NodeEntity node) {
        NodeEntity nodeFromDb = nodeRepository.findById(node.getId()).orElseThrow(NullPointerException::new);
        return nodeRepository.save(node);
    }

    public void delete(long id) {
        NodeEntity node = nodeRepository.findById(id).orElseThrow(NullPointerException::new);
        nodeRepository.delete(node);
    }

    public List<NodeEntity> findNodesInRadius(Double latitude, Double longitude, Double radius) {
        return nodeRepository.getNodesInRadius(latitude, longitude, radius);
    }
}
