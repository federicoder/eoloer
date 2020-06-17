import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { AssegnazioneTaskComponent } from 'app/entities/assegnazione-task/assegnazione-task.component';
import { AssegnazioneTaskService } from 'app/entities/assegnazione-task/assegnazione-task.service';
import { AssegnazioneTask } from 'app/shared/model/assegnazione-task.model';

describe('Component Tests', () => {
  describe('AssegnazioneTask Management Component', () => {
    let comp: AssegnazioneTaskComponent;
    let fixture: ComponentFixture<AssegnazioneTaskComponent>;
    let service: AssegnazioneTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [AssegnazioneTaskComponent],
      })
        .overrideTemplate(AssegnazioneTaskComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AssegnazioneTaskComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AssegnazioneTaskService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AssegnazioneTask(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.assegnazioneTasks && comp.assegnazioneTasks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
