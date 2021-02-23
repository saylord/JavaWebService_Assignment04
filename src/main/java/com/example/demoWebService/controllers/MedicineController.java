package com.example.demoWebService.controllers;

import com.example.demoWebService.entities.Medicine;
import com.example.demoWebService.repositories.interfaces.IMedicineRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("medicines")
public class MedicineController {
    @Inject
    private IMedicineRepository repo;

    @GET
    @Path("/search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchMedicine(@PathParam("name") String name) {
        List<Medicine> medicines;
        try {
            medicines = repo.searchMedicine(name);
        } catch (ServerErrorException e) {
            return Response
                    .status(500).entity(e.getMessage()).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicines)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicine(@PathParam("id") int id) {
        Medicine medicine;
        try {
            medicine = repo.getMedicine(id);
        } catch (ServerErrorException e) {
            return Response
                    .status(500).entity(e.getMessage()).build();
        }

        if (medicine == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Medicine does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicine)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMedicine(Medicine medicine) {
        boolean added;
        try {
            added = repo.addMedicine(medicine);
        } catch (ServerErrorException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }

        if (!added) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Medicine cannot be added!")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity("Medicine added successfully!")
                .build();
    }

    @DELETE
    @Path("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeMedicine(@PathParam("id") int id) {
        boolean deleted;
        try {
            deleted = repo.removeMedicine(id);
        } catch (ServerErrorException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }

        if (!deleted) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Medicine cannot be deleted!")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity("Medicine deleted successfully!")
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicines() {
        List<Medicine> medicines;
        try {
            medicines = repo.getAllMedicines();
        } catch (ServerErrorException e) {
            return Response
                    .status(500).entity(e.getMessage()).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicines)
                .build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeExpiredMedicine() {
        boolean removeExM;
        try {
            removeExM = repo.removeExpiredMedicine();
        } catch (ServerErrorException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }

        if (!removeExM) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Expired medicine cannot be deleted!")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity("Expired medicine deleted successfully!")
                .build();
    }

    @GET
    @Path("/expired")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllExpiredMedicines() {
        List<Medicine> medicines;
        try {
            medicines = repo.getAllExpiredMedicines();
        } catch (ServerErrorException e) {
            return Response
                    .status(500).entity(e.getMessage()).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicines)
                .build();
    }
}
