package uz.data.codingbat.services;

import uz.data.codingbat.entities.Part;
import uz.data.codingbat.payloads.PartDTO;
import uz.data.codingbat.templates.Result;

import java.util.List;

public interface PartService {

    List<Part> getPartList();

    Part getPart(Integer partId);

    Result addPart(PartDTO partDTO);

    Result updatePart(PartDTO partDTO, Integer partId);

    Result deletePart(Integer partId);

}
