# how to use the logger

```
    private static final Logger log = LoggerFactory.getLogger(ReservationService.class);

    public List<Reservation> findAll() {
        log.info("finding all reservations");

        return reservationRepository.findAll();
    }
```

the result will be:

2021-10-28 20:11:58.666 INFO 815502 --- [  restartedMain] com.sda.project.service.ReservationService               :
finding all reservations