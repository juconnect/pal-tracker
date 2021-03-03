package io.pivotal.pal.tracker;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

 @RestController
 @RequestMapping
 public class TimeEntryController {

     private TimeEntryRepository timeEntryRepository;

     public TimeEntryController(TimeEntryRepository timeEntryRepository) {
         this.timeEntryRepository = timeEntryRepository;
     }

     @PostMapping("/time-entries")
     public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
         TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
         return ResponseEntity.created(null).body(timeEntry);
     }

     @GetMapping("/time-entries/{timeEntryId}")
     public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
         TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
         if (timeEntry==null){
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(timeEntry);
     }

     @GetMapping(("/time-entries"))
     public ResponseEntity<List<TimeEntry>> list() {
         ResponseEntity<List<TimeEntry>> response = new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
         return response;

     }

     @PutMapping("time-entries/{timeEntryId}")
     public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,@RequestBody TimeEntry timeEntryToUpdate) {
         TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
         if (timeEntry==null){
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(timeEntry);
     }

     @DeleteMapping("time-entries/{timeEntryId}")
     public ResponseEntity<TimeEntry> delete(@PathVariable Long timeEntryId) {
         ResponseEntity<TimeEntry> response = null;
         timeEntryRepository.delete(timeEntryId);
         return ResponseEntity.noContent().build();

     }
 }



