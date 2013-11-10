package solid5ive.cams.service.contracts;

import org.joda.time.DateTime;
import solid5ive.cams.data.entities.Menu;

import java.util.List;
import solid5ive.cams.data.entities.MenuTag;

/**
 * Service layer that enables stall owners to edit their menu.
 * 
 * @author Poh Shie Liang
 */
public interface MenuService {
    public List<Menu> listMenus(Integer stallId);
    public List<Menu> listMenus(Integer stallId, DateTime refDateTime);
    public Menu createMenu(Integer stallId, String dishName, Double price, MenuTag tag, String description);
    public Menu updateMenu(Integer menuId, String dishName, Double price, MenuTag tag, String description);
    public void deleteMenu(Integer menuId);
}
