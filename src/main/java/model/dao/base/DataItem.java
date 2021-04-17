package model.dao.base;

import lombok.*;

/**
 * @author ：Yubo Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class DataItem {
    @NonNull private Long id;
}
