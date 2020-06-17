import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { AllegatoTemplateTaskComponent } from 'app/entities/allegato-template-task/allegato-template-task.component';
import { AllegatoTemplateTaskService } from 'app/entities/allegato-template-task/allegato-template-task.service';
import { AllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';

describe('Component Tests', () => {
  describe('AllegatoTemplateTask Management Component', () => {
    let comp: AllegatoTemplateTaskComponent;
    let fixture: ComponentFixture<AllegatoTemplateTaskComponent>;
    let service: AllegatoTemplateTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [AllegatoTemplateTaskComponent],
      })
        .overrideTemplate(AllegatoTemplateTaskComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AllegatoTemplateTaskComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AllegatoTemplateTaskService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AllegatoTemplateTask(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.allegatoTemplateTasks && comp.allegatoTemplateTasks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
