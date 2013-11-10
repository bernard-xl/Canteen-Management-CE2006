package solid5ive.cams.service.contracts;

import solid5ive.cams.data.entities.Canteen;
import solid5ive.cams.data.entities.Stall;

import java.util.List;

/**
 * Service layer that provides various service related to infrastructure management.
 * Note that our system doesn't suppose to create or delete any infrastructure information (That is managed by school/higher level department),
 * those methods are exposed here for ease of testing and demostration only.
 * 
 * @author Poh Shie Liang
 */
public interface InfrastructureService {
    public List<Canteen> listCanteens();
    public Canteen findCanteen(String name);
    public Canteen createCanteen(String name);
    public Canteen updateCanteen(Integer canteenId, String name);
    public void deleteCanteen(Integer canteenId);

    public List<Stall> listStalls();
    public List<Stall> listStalls(Integer canteenId);
    public List<Stall> listStallByOwner(String loginName);
    public Stall createStall(Integer canteenId, String lotNumber);
    public Stall updateStall(Integer stallId, String ownerLogin, String stallName, String foodStyle);
    public void deleteStall(Integer stallId);
}
