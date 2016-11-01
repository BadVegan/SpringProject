package pl.View;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import pl.model.City;
import pl.model.Customer;
import pl.service.CityService;
import pl.service.CustomerService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by davit on 30.10.2016.
 */
@Named
@ViewScoped
public class CustomerView implements Serializable {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CityService cityService;

    private City selectedCity;

    private List<Customer> customersList;
    private List<City> citiesList;

    private String customerName;
    private String cityName;

    @PostConstruct
    public void init() {
        loadData();
    }

    public void onRowEdit(RowEditEvent event) {
        Customer customer = (Customer) event.getObject();
        if (selectedCity != null) {
            customer.setCity(selectedCity);
            selectedCity = null;
        }
        customerService.updateCustomer(customer);
        loadData();
    }

    public void deleteAction(Customer customer) {
        customerService.deleteCustomer(customer);
        loadData();
    }

    public void addCity() {
        City newCity = new City(cityName);
        try {
            cityService.addCity(newCity);
        } catch (DataIntegrityViolationException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "City not unique"));
        }
        cityName = null;
        loadData();
    }

    public void addCustomer() {
        Customer newCustomer = new Customer(customerName);
        try {
            customerService.addCustomer(newCustomer);
        } catch (DataIntegrityViolationException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Customer not unique"));
        }
        customerName = null;
        loadData();
    }

    private void loadData() {
        customersList = customerService.getAllCustomers();
        citiesList = cityService.getAllCities();
    }

    public List<Customer> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(List<Customer> customersList) {
        this.customersList = customersList;
    }

    public List<City> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<City> citiesList) {
        this.citiesList = citiesList;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
