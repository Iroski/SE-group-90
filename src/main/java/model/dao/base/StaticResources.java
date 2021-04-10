package model.dao.base;

import lombok.*;

/**
 * @author ：Yubo Wang
 * @date ：2021-04-10 15:31
 * @description：
 * @modified By：
 * @version:
 */
@Data()
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class StaticResources extends DataItem {
    /**
     * Constructor with item id.
     * @author Yubo Wang
     * @date 2021-04-10 15:37
     * @param id
     * @return null
     */
    public StaticResources(Long id) {
        super(id);
    }
    /**
     * Constructor without item id.
     * @author Yubo Wang
     * @date 2021-04-10 15:36
     * @return null
     */
    public StaticResources() {    }
    /**
     *
     * @author Yubo Wang
     * @date 2021-04-10 15:34 
     */
    private void parseName() {
    }
}
