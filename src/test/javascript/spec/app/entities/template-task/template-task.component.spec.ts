import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { TemplateTaskComponent } from 'app/entities/template-task/template-task.component';
import { TemplateTaskService } from 'app/entities/template-task/template-task.service';
import { TemplateTask } from 'app/shared/model/template-task.model';

describe('Component Tests', () => {
  describe('TemplateTask Management Component', () => {
    let comp: TemplateTaskComponent;
    let fixture: ComponentFixture<TemplateTaskComponent>;
    let service: TemplateTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TemplateTaskComponent],
      })
        .overrideTemplate(TemplateTaskComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TemplateTaskComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TemplateTaskService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TemplateTask(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.templateTasks && comp.templateTasks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
