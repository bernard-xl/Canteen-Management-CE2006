package solid5ive.cams.service.internals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solid5ive.cams.data.entities.Canteen;
import solid5ive.cams.data.entities.Stall;
import solid5ive.cams.data.entities.User;
import solid5ive.cams.data.repositories.CanteenRepository;
import solid5ive.cams.data.repositories.StallRepository;
import solid5ive.cams.data.repositories.UserRepository;
import solid5ive.cams.service.contracts.InfrastructureService;

import java.util.List;

@Service
@Transactional
public class InfrastructureServiceImpl implements InfrastructureService {

    @Autowired
    private CanteenRepository canteenRepository;

    @Autowired
    private StallRepository stallRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Canteen> listCanteens() {
        return canteenRepository.findAll();
    }
    
    @Override
    public Canteen findCanteen(String name) {
        return canteenRepository.findByName(name);
    }

    @Override
    public Canteen createCanteen(String name) {
        Canteen canteen = canteenRepository.findByName(name);
        if(canteen != null) throw new IllegalArgumentException("The canteen already exist.");

        canteen = new Canteen(name);
        canteenRepository.save(canteen);
        return canteen;
    }

    @Override
    public Canteen updateCanteen(Integer canteenId, String name) {
        Canteen canteen = canteenRepository.findOne(canteenId);
        if(canteen == null) throw new IllegalArgumentException("The canteen doesn't exist.");

        canteenRepository.save(canteen);
        return canteen;
    }

    @Override
    public void deleteCanteen(Integer canteenId) {
        Canteen canteen = canteenRepository.findOne(canteenId);
        if(canteen == null) throw new IllegalArgumentException("The canteen doesn't exist.");

        canteenRepository.delete(canteen);
    }
    
    @Override
    public List<Stall> listStalls() {
        return stallRepository.findAll();
    }

    @Override
    public List<Stall> listStalls(Integer canteenId) {
        Canteen canteen = canteenRepository.findOne(canteenId);
        if(canteen == null) throw new IllegalArgumentException("The canteen doesn't exist.");

        return stallRepository.findByCanteen(canteen);
    }

    @Override
    public List<Stall> listStallByOwner(String loginName) {
        User owner = userRepository.findOne(loginName);
        if(owner == null) throw new IllegalArgumentException("The user doesn't exist.");

        return stallRepository.findByOwner(owner);
    }

    @Override
    public Stall createStall(Integer canteenId, String lotNumber) {
        Canteen canteen = canteenRepository.findOne(canteenId);
        if(canteen == null) throw new IllegalArgumentException("The canteen doesn't exist.");

        Stall stall = new Stall(canteen, lotNumber);
        stallRepository.save(stall);
        return stall;
    }

    @Override
    public Stall updateStall(Integer stallId, String ownerLogin, String stallName, String foodStyle) {
        Stall stall = stallRepository.findOne(stallId);
        if(stall == null) throw new IllegalArgumentException("The stall doesn't exist.");

        User user = null;
        if(ownerLogin != null) {
            user = userRepository.findOne(ownerLogin);
            if(user == null) throw new IllegalArgumentException("The user doesn't exist.");
        }

        stall.setOwner(user);
        if(stallName != null) stall.setName(stallName);
        if(foodStyle != null) {
            List<Stall> otherStall = stallRepository.findByFoodStyleInCanteen(stall.getCanteen(), foodStyle);
            if(!otherStall.isEmpty())
                throw new IllegalArgumentException("Another stall in this canteen is selling the same type of food.");
        }
        stall.setFoodStyle(foodStyle);
        stallRepository.save(stall);
        return stall;
    }

    @Override
    public void deleteStall(Integer stallId) {
        Stall stall = stallRepository.findOne(stallId);
        if(stall == null) throw new IllegalArgumentException("The stall doesn't exist.");

        stallRepository.delete(stall);
    }
}
