import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { AllegatoTaskComponent } from 'app/entities/allegato-task/allegato-task.component';
import { AllegatoTaskService } from 'app/entities/allegato-task/allegato-task.service';
import { AllegatoTask } from 'app/shared/model/allegato-task.model';

describe('Component Tests', () => {
  describe('AllegatoTask Management Component', () => {
    let comp: AllegatoTaskComponent;
    let fixture: ComponentFixture<AllegatoTaskComponent>;
    let service: AllegatoTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [AllegatoTaskComponent],
      })
        .overrideTemplate(AllegatoTaskComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AllegatoTaskComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AllegatoTaskService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AllegatoTask(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.allegatoTasks && comp.allegatoTasks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
