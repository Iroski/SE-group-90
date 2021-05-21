package model.entity;

import lombok.*;
import model.dao.base.DataItem;
import model.enumPackage.ImageType;

/**
 * @author ：Yubo Wang
 * @date ：2021-05-21 18:41
 * @description：
 * @modified By：
 * @version:
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StaticImage extends DataItem {
    private String filePath;
    private String name;
    private ImageType type;
    public StaticImage(String filePath, String name, ImageType type) {
        this.filePath = filePath;
        this.name = name;
        this.type = type;
    }

    public StaticImage(@NonNull Long id, String filePath, String name, ImageType type) {
        super(id);
        this.filePath = filePath;
        this.name = name;
        this.type = type;
    }
}
