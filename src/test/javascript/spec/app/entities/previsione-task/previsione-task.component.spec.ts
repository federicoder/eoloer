import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { PrevisioneTaskComponent } from 'app/entities/previsione-task/previsione-task.component';
import { PrevisioneTaskService } from 'app/entities/previsione-task/previsione-task.service';
import { PrevisioneTask } from 'app/shared/model/previsione-task.model';

describe('Component Tests', () => {
  describe('PrevisioneTask Management Component', () => {
    let comp: PrevisioneTaskComponent;
    let fixture: ComponentFixture<PrevisioneTaskComponent>;
    let service: PrevisioneTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PrevisioneTaskComponent],
      })
        .overrideTemplate(PrevisioneTaskComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrevisioneTaskComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisioneTaskService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PrevisioneTask(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.previsioneTasks && comp.previsioneTasks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
