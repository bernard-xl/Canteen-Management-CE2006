package solid5ive.cams.service.internals;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solid5ive.cams.data.entities.Menu;
import solid5ive.cams.data.entities.MenuReference;
import solid5ive.cams.data.entities.Stall;
import solid5ive.cams.data.repositories.MenuRefRepository;
import solid5ive.cams.data.repositories.MenuRepository;
import solid5ive.cams.data.repositories.StallRepository;
import solid5ive.cams.service.contracts.MenuService;

import java.util.List;
import solid5ive.cams.data.entities.MenuTag;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRefRepository menuRefRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private StallRepository stallRepository;

    @Override
    public List<Menu> listMenus(Integer stallId) {
        Stall stall = stallRepository.findOne(stallId);
        if(stall == null) throw new IllegalArgumentException("The stall doesn't exist.");

        return menuRepository.findByStall(stall);
    }

    @Override
    public List<Menu> listMenus(Integer stallId, DateTime refDateTime) {
        Stall stall = stallRepository.findOne(stallId);
        if(stall == null) throw new IllegalArgumentException("The stall doesn't exist.");

        return menuRepository.findUpdatedByStall(stall, refDateTime);
    }

    @Override
    public Menu createMenu(Integer stallId, String dishName, Double price, MenuTag tag, String description) {
        Stall stall = stallRepository.findOne(stallId);
        if(stall == null) throw new IllegalArgumentException("The stall doesn't exist.");

        if(Double.compare(price, 0.00) < 0)
            throw new IllegalArgumentException("The price of can't be negative.");
        
        MenuReference reference = new MenuReference(dishName, price);
        Menu menu = new Menu(reference, stall);
        menu.setTag(tag);
        menu.setDescription(description);
        menuRepository.save(menu);

        return menu;
    }

    @Override
    public Menu updateMenu(Integer menuId, String dishName, Double price, MenuTag tag, String description) {
        Menu menu = menuRepository.findOne(menuId);
        if(menu == null) throw new IllegalArgumentException("The menu doesn't exists.");

        if(Double.compare(price, 0.00) < 0)
            throw new IllegalArgumentException("The price of can't be negative.");
        
        MenuReference oldReference = menu.getReference();
        if(dishName == null) dishName = oldReference.getName();
        if(price == null) price = oldReference.getPrice();

        if(!dishName.equals(oldReference.getName()) || price != oldReference.getPrice()) {
            MenuReference newReference = new MenuReference(dishName, price);
            menu.setReference(newReference);
        }
        
        menu.setTag(tag);
        menu.setDescription(description);
        menu.setLastModified(new DateTime());

        menuRepository.save(menu);
        return menu;
    }

    @Override
    public void deleteMenu(Integer menuId) {
        Menu menu = menuRepository.findOne(menuId);
        if(menu == null) throw new IllegalArgumentException("The menu doesn't exists.");

        menuRepository.delete(menu);
    }
}
