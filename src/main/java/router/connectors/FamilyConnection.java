package router.connectors;


import router.models.Family;

public interface FamilyConnection {
    Family getFamily(long id);

    Family getStubFamily(long familyId);

    Family createFamily(Family family);
}
