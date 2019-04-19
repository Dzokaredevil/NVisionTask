package com.voitovich.NVisionTask;

import com.voitovich.NVisionTask.data.Job;
import com.voitovich.NVisionTask.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
class JobService {
    @Autowired
    private JobRepository jobRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private List<Job> findByUserByDeviceByTypeByUtc(String user, String device, String type, Date timeFrom, Date timeTo) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Job> cr = cb.createQuery(Job.class);
        Root<Job> root = cr.from(Job.class);

        List<Predicate> predicates = new LinkedList<>();
        if (user != null) {
            predicates.add(cb.equal(root.get("user"), user));
        }
        if (device != null) {
            predicates.add(cb.equal(root.get("device"), device));
        }
        if (type != null) {
            predicates.add(cb.equal(root.get("type"), type));
        }
        if (type != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("time"), timeTo));
        }
        if (type != null) {
            predicates.add(cb.greaterThan(root.get("time"), timeTo));
        }

        cr.select(root).where(predicates.toArray(new Predicate[0])).orderBy(cb.asc(root.get("time")));
        return entityManager.createQuery(cr).getResultList();
    }

    Map<String, Long> saveJobs(ArrayList<Job> jobs) {

        Map<String, Long> res = new HashMap<>();
        for (Job j : jobs) {
            if (!res.containsKey(j.getUser())) {
                res.put(j.getUser(), j.getAmount());
            } else {
                res.put(j.getUser(), res.get(j.getUser()) + j.getAmount());
            }
        }
        jobRepository.saveAll(jobs);
        return res;
    }


    List<Job> search(String user, String device, String type, Date timeFrom, Date timeTo) {

        return findByUserByDeviceByTypeByUtc(user, device, type, timeFrom, timeTo);
    }
}
