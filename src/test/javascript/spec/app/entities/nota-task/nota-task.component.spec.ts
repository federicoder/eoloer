import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { NotaTaskComponent } from 'app/entities/nota-task/nota-task.component';
import { NotaTaskService } from 'app/entities/nota-task/nota-task.service';
import { NotaTask } from 'app/shared/model/nota-task.model';

describe('Component Tests', () => {
  describe('NotaTask Management Component', () => {
    let comp: NotaTaskComponent;
    let fixture: ComponentFixture<NotaTaskComponent>;
    let service: NotaTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [NotaTaskComponent],
      })
        .overrideTemplate(NotaTaskComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotaTaskComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotaTaskService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NotaTask(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.notaTasks && comp.notaTasks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
