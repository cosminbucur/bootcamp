# how to implement findAll()

    // var 1: jdbc prepared statements

    // var 2: hibernate query

    // var 3: spring data jpa repository
    // reservation repository extends jpa repository

    // SELECT * FROM reservation;
        // if no data in table, return empty list
        // if yes, return list
    
    // override = change body of method
    // overload = change signature
    List<Reservation> findAll();
    
    // optional - used when an entity might not be present in db
    
    // var 1: custom query
    
    // HQL - hibernate query language (works with entities)
    // FROM Reservation as r WHERE r.id = :id
    
    // SQL - native sql code (works with tables)
    // SELECT * FROM reservation WHERE id = :id
    
    @Query("FROM Reservation as r WHERE r.id = :id")
    Reservation findByIdCustom(Long id);
    
    // var 2: derived query
    Reservation findById(Long id);
    
    Reservation findByPlace(String place);