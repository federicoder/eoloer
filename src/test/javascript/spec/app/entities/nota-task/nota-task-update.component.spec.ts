import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { NotaTaskUpdateComponent } from 'app/entities/nota-task/nota-task-update.component';
import { NotaTaskService } from 'app/entities/nota-task/nota-task.service';
import { NotaTask } from 'app/shared/model/nota-task.model';

describe('Component Tests', () => {
  describe('NotaTask Management Update Component', () => {
    let comp: NotaTaskUpdateComponent;
    let fixture: ComponentFixture<NotaTaskUpdateComponent>;
    let service: NotaTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [NotaTaskUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NotaTaskUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotaTaskUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotaTaskService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NotaTask(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new NotaTask();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
