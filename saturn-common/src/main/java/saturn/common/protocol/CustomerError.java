package saturn.common.protocol;

public class CustomerError extends SaturnMessage<SaturnErrorData> {
    public CustomerError(){
        setType("CUSTOMER_ERROR");
        setData(new SaturnErrorData());
        getData().setCode("customer.notFound");
        getData().setDescription("Customer not found");
    }
}
