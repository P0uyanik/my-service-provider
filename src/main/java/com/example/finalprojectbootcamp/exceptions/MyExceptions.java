package com.example.finalprojectbootcamp.exceptions;

import com.example.finalprojectbootcamp.core.entities.*;
import com.example.finalprojectbootcamp.core.enums.ExpertStatus;
import com.example.finalprojectbootcamp.core.enums.OfferStatus;
import com.example.finalprojectbootcamp.core.enums.OrderStatus;

import java.util.List;

public class MyExceptions {
    public static void isServiceAvailable(Service service) throws ServiceNotFoundException {
        if (service == null)
            throw new ServiceNotFoundException();
    }

    public static void isExpertExists(Expert expert) {
        if (expert == null)
            throw new ExpertNotFoundException();
    }

    public static void pageSizeIsNotCorrect(int pageSize) {
        if (pageSize == 0 || pageSize < 0)
            throw new PageSizeException();
    }


    public static void couldNotBeFound(List<Expert> expertByExpertStatus) {
        if (expertByExpertStatus.size() == 0)
            throw new ExpertNotFoundException();
    }

    public static void isSubServiceAvailable(SubService mySubservice) {
        if (mySubservice == null)
            throw new SubServiceNotFoundException();
    }

    public static void isCustomerRegistered(Customer customerById) {
        if (customerById == null)
            throw new CustomerNotFoundException();
    }

    public static void expertAccess(boolean accessToTheSystem) {
        if (!accessToTheSystem) {
            throw new ExpertAccessException();
        }
    }

    public static void isOrderExists(Order orderById) {
        if (orderById == null)
            throw new OrderNotFoundException();
    }

    public static void isOfferExists(Offer offer) {
        if (offer == null)
            throw new OfferNotFoundException();
    }

    public static void checkOfferOrderExpertRelation(Customer customerById, Expert expertById, Offer offerById, Order orderById) {
        for (Offer expertByIdOffer : expertById.getOffers()) {
            if (expertByIdOffer.hashCode() == offerById.hashCode())
                if (expertByIdOffer.getOrder().hashCode() == orderById.hashCode())
                    if (expertByIdOffer.getOrder().getCustomer().hashCode() == customerById.hashCode())
                        return;
        }
        throw new RateAndReviewException();
    }

    public static List<Offer> checkOrderForCustomer(Customer customerById, Order orderById) {
        for (Order order : customerById.getOrders()) {
            if (order.hashCode() == orderById.hashCode())
                return order.getOffers();


        }
        throw new OrderNotFoundForCustomer();
    }

    public static void checkOffers(List<Offer> offers) {
        for (Offer offer : offers) {
            if (offer.getOfferStatus().equals(OfferStatus.ACTIVE)) {
                throw new SelectedOfferException();
            }

        }
    }

    public static Offer checkOfferForOrder(List<Offer> offers, Offer offer) {
        for (Offer offer1 : offers)
            if (offer1.hashCode() == offer.hashCode())
                return offer1 ;
        throw new OfferNotMatch();
    }

    public static void checkOffersToFindActiveOffer(List<Offer> offers) {
        int sum = 0 ;
        for (Offer offer : offers)
            if (offer.getOfferStatus().equals(OfferStatus.ACTIVE))
                sum++ ;

        if (sum != 1 )
            throw new ActiveOfferException("number of offers" +sum) ;

    }

    public static void negativeRating(double rating) {
        if (rating<0)
            throw  new NegativeRatingException() ;

    }

    public static void checkStartTimeException(int i) {
        if (i < 0 )
            throw new StartTimeException() ;
    }

    public static void orderIfHasBeenCompleted(Order order) {
        if(! order.getOrderStatus().equals(OrderStatus.COMPLETED))
            throw new OrderCommentException() ;
    }

    public static void isAdminExists(Admin admin) {
        if(admin == null)
            throw new AdminNotFoundException() ;
    }

    public static void subServiceAlreadyExists(SubService subServiceByTitle) {
        if(! (subServiceByTitle == null))
            throw  new ExistsException("The sub-service with this title already exists.") ;
    }

    public static void serviceAlreadyExists(Service servicesByName) {
        if (! (servicesByName == null))
            throw new ExistsException("The service with this name already exists.") ;
    }

    public static void checkExpertStatus(ExpertStatus expertStatus) {
        if(expertStatus.equals(ExpertStatus.PENDING_APPROVAL) || expertStatus.equals(ExpertStatus.NEW) )
            throw new ExpertAccessException() ;
    }
}